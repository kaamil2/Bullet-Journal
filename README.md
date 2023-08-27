[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)

# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)
![Screenshot 2023-06-18 at 10.36.20 PM.png](..%2F..%2FScreenshot%202023-06-18%20at%2010.36.20%20PM.png)

# Pitch:
    Java Journal is a revolutionary new way to organize your week! Java Journal allows you to create a 
    weekly calendar with events and tasks filtered into categories. Want to link to a website? Just paste
    the link in and Java Journal will automatically turn it into a link while viewing the task in its own
    mini viewer!

# SOLID:
## Single Responsibility Principle:
    This principle has been followed because each class in our codebase has only 1 purpose. 
    For example, JournalDay represents a single day in a bullet journal. Another example I really like for SRP is the 
    CommitmentMonitor class. It's really simple and is contained within a BulletJournal. But this makes sure that the 
    BulletJournal class doesn't have too many responsibilities all on its own. Instead, we just call a simple
    method on the CommitmentMonitor and it takes care of this determination for us.

## Open/Closed Principle:
    One example of this principle being followed is the ScheduleItem interface (and the AbstractItem used to write less
    code / abstract appropriately. This class represents an item that can be added to a bullet journal.
    It is open to be extended with new types of items, but is closed for modification since its methods must be 
    implemented. If we had to implement a new type of item, like a Reminder, or something, we feel pretty confident
    that the actual process of adding that into the implementation would not be too demanding because we are really
    just calling methods exclusively available within the ScheduleItem (and the AbstractItem).

## Liskov Substitution Principle:
    The ScheduleItem interface is used to follow this principle with both tasks and events.
    Since both these classes ultimately implement ScheduleItem they both can take its place without any problems. 
    The program is very flexible in its use of ScheduleItems rather than calling a specific instance of ScheduleItem.
    Although we limit the SideBar / TaskView to be only Tasks, if an Event were to sneak into there somehow... the 
    program would not crash! It would just show information about the event because an interface method 
    - toString(boolean) is called to display the information.

## Interface Segregation Principle:
    The DayGuiView interface is a good example of this principle. The interface is short and only contains
    the load method. This way any implementation of this does not need to implement any unecessary methods. If we
    needed to implement alternate views that could be used flexibly, we could place this under the interface
    as well, implement the method, and not much code (beyond creating that new view) would necessarily be needed.

## Dependency Inversion Principle:
    The ScheduleItem interface being used in the addCommitment methods in SchedueItemList is an example
    where abstraction provided by the interface is depended on to be able to add both tasks and events.
    We don't have to seperately handle each specific implementation of ScheduleItem and can instead rely 
    on them being abstracted.

# Extension
- Progress Bar 
    This could be easily extended since the logic for this is mostly there. We would have to extend the 
    statistic calculation to do it for each individual day rather than the whole week, and then simply add
    ProgressBars to each day and use the percent calculation to fill it and update it. Each of the days
    has the methods needed to make these calculations easily already.

![Final View Screenshot.png](Final%20View%20Screenshot.png)