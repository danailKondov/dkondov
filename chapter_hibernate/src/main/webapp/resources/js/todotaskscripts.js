/**
 * Created on 07.05.2018.
 */

window.onload = getToDoTasks();

// выдавать все таски или только выполненные (true)
var getOnlyExecutedTasks = false;

// список тасков
var tasks;

// получаем список тасков
function getToDoTasks() {
           $.ajax("./task", {
               method: "get",
               complete: function (data) {
                   tasks = JSON.parse(data.responseText);
                   updateTable(tasks, getOnlyExecutedTasks);
               }
           })
}

// меняет положение переключателя показа только выполненных/всех тасков
function changeShowExecutedTasks() {
    if (getOnlyExecutedTasks) {
        getOnlyExecutedTasks = false;
        removeDataFromTable();
        updateTable(tasks, getOnlyExecutedTasks);
    } else {
        getOnlyExecutedTasks = true;
        removeDataFromTable();
        updateTable(tasks, getOnlyExecutedTasks);
    }
}

function updateTable(tasks, onlyIsDone) {
    // create head
    var table = document.getElementById("tablebody");
    // createTableHead(table);

    // create rows
    for(var i = 0; i < tasks.length; ++i) {
        if(onlyIsDone) {
            if (tasks[i].done) {
                createTableRaw(tasks[i], table);
            }
        } else {
            createTableRaw(tasks[i], table);
        }
    }
}

function removeDataFromTable() {
    $("#tablebody").empty();
}

function createTableRaw(task, table) {
    var tr = document.createElement("tr");

    var tdId = document.createElement("td");
    tdId.appendChild(document.createTextNode(task.id));
    tr.appendChild(tdId);

    var tdDesc = document.createElement("td");
    tdDesc.appendChild(document.createTextNode(task.desc));
    tr.appendChild(tdDesc);

    var tdCreated = document.createElement("td");
    tdCreated.appendChild(document.createTextNode(task.created));
    tr.appendChild(tdCreated);

    var tdDone = document.createElement("td");
    var checkbox = document.createElement("input");
    if (task.done) {
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("name", "isDone");
        checkbox.setAttribute("checked", "checked");
    } else {
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("name", "isDone");
    }
    tdDone.appendChild(checkbox);

    tr.appendChild(tdDone);
    table.appendChild(tr);
}

function addNewTask() {
    var msg = $("#addform").serialize();
    $.ajax({
        type: "POST",
        url: "/task",
        data: msg,
        success: function(data) {
            removeDataFromTable();
            getToDoTasks();
        },
        error:  function(xhr, str){
            alert("Возникла ошибка: " + xhr.responseCode);
        }
    });
}