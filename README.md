# Task-Scheduler
A humble app the fetched List of enigneers from my home-made API and allocates them to do do shifts for 10 days so that 2 engineers are on duty daily.
There are 2 screens. 
Browse Screen: Fetches the data from API and displays them. 
Schedule Screen: Displays the days and the engineers on duty. 

You can customise the number of days, the number of shifts, how many offdays between shifts for each engineer.

Main libraries and patterns: 
- Dagger 2
- DataBinding
- Mvp pattern
- RxJava 2
- Mockito
- Parceler
- Retrofit2
- Gson

Plus points: 
- State management for activites.
- Adaptive design.
- Minimal number of API calls.

Extensibility points: 
- Introduce other ways of scheduling work just extend the Scheduler class, plug it and play :)
- Change the settings (number of days.. etc) in core.kt file to test your solution.
