# General

doorsbridge is a java library used to control DOORS from Java.

# Basic usage

* Make sure that jacob-1.18-x64.dll or jacob-1.18-x86.dll are on the java library path. The easiest way to ensure that is to copy both files to your project root directory.
* Use DoorsApplicationFactory to acquire a DoorsApplication instance.
* Start DOORS and authenticate yourself, if needed.
* Use the DoorsApplication instance to send commands to DOORS. 

# Advanced features

## Silent mode

Silent mode does not require a running DOORS session. In silent mode, DOORS is started automatically using the '-b' command line switch for each executed command. Activate silent mode by either acquiring a silent DoorsApplication instance using the provided functions in DoorsApplicationFactory, or by manually calling initSilentMode on a non-silent DoorsApplication instance.

## Batch mode

Batch mode groups multiple commands together and executes them as a single, large dxl script. Batch mode is started by calling 'beginBatchMode()'. Commands are stored in an internal buffer until 'endBatchMode()' is called. This is particularly useful in conjunction with silent mode, since invoking 

## Output redirection

doorsbridge provides output redirection. Calling the 'print' function from dxl with active output redirection causes any messages to be redirected to a designated stream. Activate output redirection by calling DoorsApplication.redirectOutput(), providing an output stream (i.e. System.out or some FileOutputStream instance).

## Exception handling

doorsbridge provides basic exception handling. The provided dxl function 'throw(String message)' is available to all scripts. If called from a dxl script, script execution is halted and a DoorsException using the message provided by the dxl script is thrown.

# Implementing custom commands

* Extend the DoorsApplicationImpl class.
* Write custom commands. Use 'buildAndRunCommand()' to initiate the call. Use the provided callback to add your custom dxl script to the script builder.
* Create an instance of your extended DoorsApplicationImpl class and call your custom commands.