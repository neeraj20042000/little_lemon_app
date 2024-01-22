# Little Lemon Android App

Follow these steps to run this Android app in Android Studio:

## Prerequisites

- Make sure you have Android Studio installed on your machine. If not, download and install it from [Android Studio official website](https://developer.android.com/studio).

## Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/neeraj20042000/little_lemon_app.git
```

## Open Project in Android Studio

1\.  Launch Android Studio.

2\.  Click on "Open an Existing Android Studio Project."

3\.  Navigate to the location where you cloned the repository and select the root directory of the app.

4\.  Click "OK" to open the project.

## Configure Emulator or Connect a Physical Device

1\.  To use an emulator, go to "Tools" > "AVD Manager" and create a new virtual device.

2\.  If using a physical device, enable USB debugging in the developer options.

## Build and Run

1\.  Once the project is open, click on the green play button (Run) in the toolbar.

2\.  Select the target device (either the emulator or your connected physical device).

3\.  Click "OK" to build and run the app.

## View App on Emulator or Device

Wait for the build process to complete, and the app will be installed and launched on the selected emulator or device.

Congratulations! You have successfully run the app in Android Studio.

## Functionality of the application

The application is developed in Android Studio Jetpack Compose toolkit for Little Lemon restaurant. The project involves creating an onboarding flow, profile page, navigation flow between screens, register and logout functionality via data persistence using shared preferences, homepage displaying foodmenu details where menu data is fetched from remote server in JSON format, stored in local SQLite database via ROOM library, can be filtered by search phrase or categories. 
