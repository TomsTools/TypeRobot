# Background
Sometimes you want a simple automation for key strokes. One useful case (and the reason I built this little tool) is that, under certain circumstances, copy/pasting passwords for Windows machines while using the RDP protocol might not work...

# Users' Guide
Just get the latest version of the jar file and invoke it from the command line. Here is an example:
```cmd
java -jar text-robot.jar 10000 test-keys.txt
```

It is mandatory to specify the following two parameters:
1. The **delay** as milliseconds. The tool will wait for this amount of time before typing the keys for you.
2. The **file** where the characters will be fetched from.

## Known issues
* When you save the file with the characters to be typed, save it with an editor that supports UNIX EOL separator (`\n`). Otherwise, the `Enter` key will be pushed twice and that might cause undesired effects.

## Recommendations
The way I use the tool is by storing it into a folder location (together with the file that lists the keys to be pressed) and changing the input of that file when necessary.

## API usage

# Developer's Guide
This tool has been developed using:
* Apache Maven 3.6.3
* Oracle JDK 11.0.8
* Spring ToolSuite 4.8.0 using [Google's Java Styleguide](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml).

##  Testing and validation
The tool has been validated only on Windows 10 64 bit. There is a `TestCharToKeyStrokeUtils` class that can be used to validate the core functionality of the tool and a file called `test-keys.txt` that contains all the common keys.

The way I tested it was by opening a notepad, allow the unit test to "type" to that notepad, save the content and do a diff on the files... It is pretty difficult (not worthy) to automate the whole process and this is why the unit test is disabled.

# Attributions
Icons made by [Smashicons](https://www.flaticon.com/authors/smashicons) from  [Flaticon](https://www.flaticon.com/).