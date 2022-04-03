<head>
<meta name="google-site-verification" content="5zG9lqy-uxXvS-NAI0nmJouy9PFcCQCeZ_l6RjMyXCk" />
</head>

# GoNotes
A kitchen for all of your notes, where ideas💡 line up. Build out the imagination into the digital world 🌎. 

|![Go Notes Launcher Icon](https://github.com/AdityaBavadekar/GoNotes/blob/master/app/ic_launcher_play_store.png)|
|----|

# Features
This app display list of notes you've created earlier. You can create new notes by tapping on '+' icon which guides you to create a new note. You can also edit the previously created notes by clicking on the notes that are shown in the list. You can delete an individual note by clicking it and then in more options menu clicking on delete or you can delete multiple notes at once by long clicking notes in the list of of notes. The notes you've deleted will remain in recycle bin for 30-days and after that they'll  be automatically deleted. You can restore them by clicking on Bin option in menu.

### Screenshots 
Not available  yet!

## Getting Started 
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### 1. Prerequisites
* [Android Studio]() 4.0 or later.
* You should have a good knowledge of [Kotlin]() language.

<!--### 2. Getting GoNotes Repo
First you need to get GoNotes Repository to you local machine or PC.
You can do either of following :
* You can clone this repo, [click here]().
* You can download .zip file of this repo by clicking [here]().
-->

### 2. Cloning Repo
Type following commands one by one in the commandline.

* Step 1
```
git clone https://github.com/AdityaBavadekar/GoNotes.git
```
* Step 2
```
cd GoNotes 
```
* Step 3
```
git status
```
If above commands did not output errors or red marks, then you're all set.

### 3.Running the app
The project contains a [`staging`]() variant that replaces some modules at compile time so they don't depend on remote services such as Firebase. This allows you to try out and test the app without the API keys.

### Development Environment
The app is written entirely in Kotlin and uses the Gradle build system.
To build the app, use the `gradlew build` command or use "Import Project" in Android Studio. 


# Versioning
We use **`X.YYYY.Z`** for versioning. For the versions available, see the [tags]() section of repository.
Where : 
* `X` is Version Code
* `YYYY` is year of latest relaese 
* `Z` is name of relaese for year YYYY. This can be any integer or integer with a -beta-integer suffix.

Eg. `1.2022.5`, `4.2023.2-beta01`

## Open Source Libraries Used
* [Room Database]()
* [Google Material Design]()
* [Firebase]()
* [Androidx]()
* [Kotlin]()

### Firebase
The app makes considerable use of the following Firebase components:

* [Cloud Firestore]() is our source for all user data (events starred or reserved by a user). Firestore gave us automatic sync and also seamlessly managed offline functionality for us.
* [Authentication]() is our authentication system. Firebase Auth gave us ability to integrate Google SignIn into our application. This Google SignIn is optional, incase the user wants to backup all the notes, we integrate with google sheets of users account to backup the notes in a format that we can retrieve them back.

### Kotlin
The app is entirely written in Kotlin and uses Jetpack's Android Ktx extensions.

Asynchronous tasks are handled with coroutines. Coroutines allow for simple and safe management of one-shot operations as well as building and consuming streams of data using Kotlin Flows.

All build scripts are written with the Kotlin DSL.




## User guide
Please read [GoNotesHows.md]() for tutorials on how to use the app, create notes etc.

# Contributing 
Please read [CONTRIBUTING.md]() for details on our code of conduct, and the process for submitting pull requests to us. 

# Authors
- [**@Aditya Bavadekar**](https://github.com/AdityaBavadekar) - Developer

See also the list of [contributors](https://github.com/AdityaBavadekar/GoNotes/contributors) who participated in this project.


# License 
```

Copyright 2021 Aditya Bavadekar.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.

```
See the [LICENSE.md]() file for details.

<meta name="google-site-verification" content="-tze8DtEhCn3b672AdDl88xvBfgPL7jcrgIvMMphvfo" />
