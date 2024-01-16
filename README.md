# Test Task

## Overview

This Android application is designed to fetch and display data from the Flickr public photo feed. It follows Google's recommended architecture approach, including MVVM (Model-View-ViewModel) and Repositories. The application is written in Kotlin and utilizes Kotlin Flows for handling asynchronous operations.

## Requirements

- Kotlin as the primary programming language.
- Utilization of Google's architecture components: MVVM and Repositories.
- Data fetching and caching for offline availability. Data should be reloaded on each app start.
- Layouts implemented in XML (not Jetpack Compose).
- Display data in a vertical RecyclerView list, with one column on smartphones and three columns on larger screens.
- Each item in the list should display its image with a description underneath.
- Clicking on an item should open the link associated with the photo in an external browser.
- Data should be sorted by the published field.

## Implementation

- Room Database
- Retrofit
- Hilt (Dependency Injection)

### Language and Architecture

The application is implemented in Kotlin, following the MVVM architecture pattern. Repositories are used to manage data fetching and caching, and Kotlin Flows are employed for handling asynchronous tasks.

### Data Source

The application fetches data from the Flickr public photo feed API:
[https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1]