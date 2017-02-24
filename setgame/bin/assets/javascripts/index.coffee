$ ->
 $.get "/tasks", (data) ->       //Takes the data from tasks 
  $.each data, (index, task) ->      //runs each data through index and task
   $("#tasks").append $("<li>").text task.contents      //displays the newly made tasks on the page