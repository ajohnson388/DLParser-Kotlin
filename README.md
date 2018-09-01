# DLParser

> DLParser is a library for parsing barcode data from AAMVA compliant driver licenses.

[![Kotlin Version][kotlin-image]][kotlin-url]
[![License][license-image]][license-url]
[![Platform][platform-image]][platform-url]

DLParser aims to decode barcode data from AAMVA compliant driver licenses. The parser
supports all AAMVA versions and AAMVA defined fields.

## Features

- [x] Support for all AAMVA standards (v1-9)
- [x] Support for all AAMVA fields
- [x] License model for all AAMVA fields
- [x] Offline use

## Requirements

- API level 16 or higher
- Kotlin 1.2
- Android Studio 3

## Installation

### JitPack

Add the dependency to the app-level build.gradle file

`implementation 'com.github.ajohnson388:DLParser-Kotlin:1.0.0'`

## Sample Installation

Clone the repo.

Sync Gradle.

Launch a debug build on a physical device or an emulator using a webcam.

Scan driver license barcodes by using the example app and view output through the console.

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
