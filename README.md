# Description

This project demonstrates performance difference regarding SQLite operations between Kotlin and C++ implementations.

It contains Kotlin Multiplatform Project including SQLDelight and an Swift XCode project with framework containing C++ SQLite wrapper (which also includes Objective C wrapper for Swift - C++ interop).

Both Kotlin and C++ use SQLChipher 3.4.2

Performance test includes bulk inserts of 100 000 entities (using `INSERT`) and reading them into native structures afterwards (using `SELECT`)

# Perfomance results using _iPhone SE_

|  Op      | Kotlin | C++         |
| -------- | -------| ----------- |
| `INSERT` | ~17sec | **~3sec**   |
| `SELECT` | ~3sec  | **~0.4sec** |
