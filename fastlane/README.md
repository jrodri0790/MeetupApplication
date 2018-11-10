fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android clean
```
fastlane android clean
```
Clean the project
### android unit
```
fastlane android unit
```
Runs the unit tests
### android lint
```
fastlane android lint
```
Runs the lint tests
### android functional
```
fastlane android functional
```
Runs the functional tests
### android build_staging
```
fastlane android build_staging
```
Builds a new staging release
### android build_prod
```
fastlane android build_prod
```
Builds a new prod release
### android deliver_staging
```
fastlane android deliver_staging
```
Delivers a new staging release
### android deliver_prod
```
fastlane android deliver_prod
```
Delivers a new prod release

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
