# RepoDisplay

This is meta project that displays the list of pull requests closed in the repository.

### Features
 - Dark mode handling
 - Error states handling
 - Empty states handling
 - Configuration changes handling
 - Uses MVVM pattern, LiveData and Jetpack ViewModel

### Installation
 - Clone this repository
```
git clone https://github.com/mishrabhilash/RepoDisplay.git
```
 - Change Gradle JDK to Java 11 
![gradle setting](https://github.com/mishrabhilash/RepoDisplay/blob/main/readmeFiles/gradle_setting.png)

 and thats it.
 
### Configuration
 - If you want to view your own repo, just go to `PRDisplayActivityViewModel` and change values of fields `user` and `repoName`.
 - If you want to change the number of pull requests fetched in a single page, change `per_page` value in `fetchPage` method of `PRDisplayActivityViewModel` class.
 
 ### Screenshots
 #### Success Response
 <img src="https://github.com/mishrabhilash/RepoDisplay/blob/main/readmeFiles/screen_shot1.jpeg" width="300">
 
  #### Failure Response
 <img src="https://github.com/mishrabhilash/RepoDisplay/blob/main/readmeFiles/screen_shot2.jpeg" width="300">
