How to run SLCSP Java Application:

1. Install Java. (See Appendix 1)
2. Install Eclipse (Optional - See Appendix 1)
3. Create runnable Jar file (See Appendix 2)
4. Run jar file from directory containing input and output folders
a. input folder should contain: slcsp.csv, plans.csv, and zipcodes.csv
b. after application runs, output folder should contain slcsp.csv file with the correct rate
values
5. If having trouble compiling and running jar file? Please use jar file included in slcspRunnable.zip.


Appendix 1:
Preparing Linux to run Eclipse:
1. Install Java using apt (alternative is to install from oracle's site).
Install OpenJDK using apt
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default
java - version
2. Download Eclipse from its website (or install directly from software store per OS -if successful skip remaining eclipse steps).
Check your OS Type, 32-bit or 64-bit, by going to System Settings -> Details -> Overview, then select download Linux 32-bit or 64-bit 
of Eclipse Installer.
3. Run the installer wizard.
Decompress the downloaded archive in your file browser and navigate to the result “eclipse-installer” folder, right-click on file 
eclipse-inst and select Run.
Don’t see the “Run” option? Go to file browser’s Menu Edit -> Preferences -> Behavior -> check “Run executable text files when they 
are opened”, and finally log out and back in (or run nautilus -q command in terminal). 
When the wizard launches, select install item, then installation folder, and finally click INSTALL button.
You may select Advanced Mode from top-right menu to get more options, network proxy settings, etc.
4. Create a launcher shortcut.
Open terminal from Unity Dash, Application Launcher, or via Ctrl+Alt+T shortcut key. When it opens, paste below command and run:
gedit .local/share/applications/eclipse.desktop
The command creates and opens a launcher file, for current user, with gedit text editor. When it opens, paste below content:
[Desktop Entry]
Name=Eclipse
Type=Application
Exec=/home/USERNAME/java-mars/eclipse/eclipse
Terminal=false
Icon=/home/USERNAME/java-mars/eclipse/icon.xpm
Comment=Integrated Development Environment
NoDisplay=false
Categories=Development;IDE;
Name[en]=Eclipse
Depends on your installation folder, check out in file browser, change the value in red.
Credit - Steps retrieved from: http://ubuntuhandbook.org/index.php/2016/01/how-to-install-the-latest-eclipse-in-ubuntu-16-04-15-10/


Appendix 2:
Create executable jar file from slcsp application in Eclipse:
Select the class containing the main method (Main.java) in the app package.
Right click on the class containing the main method and select Export.
Expand the Java folder and select "Runnable JAR file" and then click "Next".
Select the Launch configuration: "Main - AdHoc_Homework_SLCSP" option from the list.
Choose an export destination.
Select the "Extract required libraries into generated JAR" option under "Library handling:"
Click on "Finish" and the JAR file will appear in the export destination.

To run the JAR you will need to have an empty output folder and an input folder with the original (blank rates) slcsp.csv file, 
zips.csv file, and plans.csv file located in the same location as the runnable JAR. 
To run from desktop: Double click on the slcsp.jar file and the 
program will generate the slcsp.csv (with rates) file in the output folder.
To run from command prompt: open terminal and type "java -jar slcsp.jar" (java -jar app.jar)
