# DLParser

> DLParser is a library for parsing barcode data from AAMVA compliant driver licenses.

[![Kotlin Version][kotlin-image]][kotlin-url]
[![License][license-image]][license-url]
[![Platform][platform-image]][platform-url]

DLParser aims to decode barcode data from AAMVA compliant driver licenses. The parser
supports all AAMVA versions and AAMVA defined fields.

## Features

- [x] Support for all AAMVA standards (v1-11)
- [x] Support for all AAMVA fields
- [x] License model for all AAMVA fields
- [x] Offline use
- [x] Unit tests for version parsers

## Requirements

- API level 34 or higher
- Kotlin 2.2
- Android Studio 2025.1.1

## Installation

### JitPack

Add the dependency to the app-level build.gradle file

`implementation 'com.github.ajohnson388:DLParser-Kotlin:2.0.0'`

## Sample Installation

Clone the repo.

Sync Gradle.

Launch a debug build on a physical device or an emulator using a webcam.

Scan driver license barcodes by using the example app and view output through the console.

NOTE: The example app uses the `GmsBarcodeScanner`. This scanner requires the barcode to be scanned in landscape orientation and must be presented closely to the camera for capture to work. It can take a few seconds and the barcode must be clearly presented.

## Contribute

Open an issue or make a pull request.

## Meta
Andrew Johnson – ajohnson388@gmail.com

Distributed under the MIT license. See ``LICENSE`` for more information.

[https://github.com/ajohnson388/](https://github.com/ajohnson388/)

[kotlin-image]:https://img.shields.io/badge/kotlin-1.2-orange.svg
[kotlin-url]: https://kotlinlang.org/
[license-image]: https://img.shields.io/badge/License-MIT-blue.svg
[license-url]: LICENSE
[platform-image]:https://img.shields.io/badge/platform-Android-green.svg?style=flat
[platform-url]:https://developer.android.com/
