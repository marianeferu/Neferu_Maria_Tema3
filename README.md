# Neferu_Maria_Tema3-Android-

- Load this list of 10 Users in your RecyclerView (inside a Fragment);
    ○ https://jsonplaceholder.typicode.com/users

- Select a User -> Show (In another Fragment) a List (using
RecyclerView) of it’s ToDos
    ○ Example for user with id = 1 (add user’s id at the end of the link
as value for query parameter named userId):
             ■ https://jsonplaceholder.typicode.com/todos?userId=1
    
- Select an ToDo -> Show (In Another Fragment) 3 Buttons (with
custom Shape as Background and a Selector on it’s text) and 2
TextViews:
    ○ Button1 -> Show a TimePicker to choose a time for this ToDo
and show selected time on TextView1
    
    ○ Button 2 -> Show a DatePicker to choose a date for this ToDo
and show selected time on TextView2

    ○ Button3 -> Create an Alarm using Alarm Manager to show a
Notification at selected date & time, with the title of selected
ToDo item
            ■ Show a Toast if the Notification has been successfully
              created
            ■ Close this Fragment.
