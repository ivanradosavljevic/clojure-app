# FIND NEAREST POI

This clojure project allows you to find nearest points of interest. It uses geolocation and google places api to track down your location and find poi. 

## Main features

User can:
1) Login/Register
2) After login, user can choose what kind of poi is intrested id, share location and set distance
3) Add/Remove selected poi in favourites

## Setup


1) Download and install Leiningen: http://leiningen.org/
2) Download and install MongoDB: https://www.mongodb.org/
3) Navigate to project folder and type: lein ring server

## Usage

After setup, you can register/login and reach index page, and then you can start with research.
IMPORTANT: for testing this project, you can login in with username "admin" and password "admin".

## Libraries used


1) Clostache 1.4.0
2) Compojure 1.3.1
3) Monger 3.0.0-rc2
4) Lib-noir 0.9.9
5) Ring 1.3.2
6) Clojure/data/json 0.2.6
7) Lein-Ring 0.9.1

... with Eclipse Luna nad Couterclockwize plugin.

Copyright © 2015 Ivan Radosavljevic

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
