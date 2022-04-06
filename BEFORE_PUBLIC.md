# Go Notes Ideology 
A kitchen for all of your notes, where ideas💡 line up.


## The Basic Metadata

- Author : Aditya Bavadekar 
- Idea  💡: Fri, 1st April 2022
- Source Code : https://github.com/AdityaBavadekar/GoNotes
- License: Apache License 2.0
- Platform: Android 
- Development IDE : Android Studio 
- Min Android : Kitkat 4.4
- App Name : Go Notes
- App-Type : A note-taking app
- Tag Line : A kitchen for all of your notes, where ideas💡 line up.
- Login and Sign-Up (Only with Google) : Optional
- Website : Github Pages
- Third-party App for reference : Samsung Notes (Android 11)
- Material Theming : UNKNOWN 
- Code Language  : Kotlin
- Uses Jetpack Compose  : Nope, not yet
- Architecture : MVVM
- Main Library  : Room Database 
- Contributions : Not accepted for now
- Latest Version : UNKNOWN 
- Backing up notes : Working on it but requires sign-in
- Version naming convention : `versionCode.latestReleaseYear.releaseVersionCodeForYear` (eg : 1.2022.10)
- Contains Animations: Yes
- Backend : Firebase (Only If user has loggedIn)
- Version list : 
  1. `1.2022.1-beta1` (WITH NO DESIGN AND ANIMATION, JUST ROUGH NAVIGATION AND WORKING MODEL ) 
  2. `1.2022.1-beta2 ... 1.2022.1-beta5`
  3. `1.2022.1` (STABLE INITIAL RELAESE) 

## The Flow
The Screens can be either fragments or activities. 

1. Welcome Activity 
2. Launcher Activity (or Fragment in Main)
3. Login (Only Google SignIn)
4. Sign-up (Only Google SignUp)
5. App Guidance and info
6. Setup Screen
7. Search Screen
8. Main Home Activity 
9. Back-up Fragment 
10. Recycle Bin Fragment
11. Settings Activity 
12. About Fragment 
13. Contact Us Fragment 
14. Update App Fragment
15. Export All Notes in Encrypted Form
16. Import Encrypted Notes (ONLY-GONOTES-FILES)
17. Open Source Licenses Screen (Google)
18. Create New Note Fragment
19. Edit Note Fragment
20. View Notes Fragment 
21. Add Tags Fragment 
22. View Reminders Fragment
23. Edit / Create New Reminder Fragment 
24. Recently Opened Notes Fragment
25. Favourite Notes Screen
26. TAGs /  REPOs Fragment
27. Unlock Note Fragment 
28. What's new screen
29. Help and Feedback / FAQs Activity 
30. Permissions Activity (if required)
31. Save as Dialog Activity 

### Main Screens
* Welcome Activity 
* Main Home Activity 
* Login (Only Google SignIn)
* Create New Note Fragment
* Edit Note Fragment
* View Notes Fragment 
* Recycle Bin Fragment
* Settings Activity 
* About Fragment 

On App Start : 
1. Launcher Activity > (if user hasn't got a welcome) Welcome Activity > Main Home Activity
2. View Notes Fragment > You can go everywhere.
3. Create New Note Fragment > View Notes Fragment
4. Edit Note Fragment > View Notes Fragment


## A Go Note
What Features will a note have?
- Title 
- Body
- Tags
- Creation Timestamp 
- Edited Timestamp 
- Color for Note default: Sky Blue 
- Ability to attach Image 
- Ability to attach Drawings 
- Ability to attach Voice Notes
- Ability to Fire Reminders 
- Ability to Use Markdown
- Ability to Favourite  note
- Ability to Pin notes
- Ability to Bin notes
- Ability to Archive notes
- Ability to Duplicate a note
- Ability to Lock a note 

How a Note code will look like  :
```kotlin 
{
    id=1,
    title="Hello, This is GoNotes Title",
    body="This is a GoNote body",
    tags=["GoNotes","Android","aditya"],
    created="182222299292920020022002",
    edited="182222299292920020022002",
    color="SKY_BLUE",
    isPinned=false,
    isBinned=false,
    isArchived=false,
    linksCount=0,
    links=[],
    isFavourite=false,
    isReminder=false,
    reminderStartTimestamp=null,
    reminderEndTimestamp=null,
    reminderRepeats="NONE",
    image=["img_1002.png"],
    voiceNote="vc_90006807.mp3";
    drawing="NONE",
    isTextFormattingEnabled=true,
    isLocked=true
}
```

## Note Export/Share
Options (Share as) :
- Go Notes File
- Text File
-  Image File
-  PDF File
- Copy to Google Docs

## Google Drive Api
[Drive Api Documentation](https://developers.google.com/drive/api/guides/appdata)




