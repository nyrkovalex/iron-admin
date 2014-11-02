iron-admin
==========

Declarative web application admin interface in Java. Deeply work in progress


# How to get things up and running

First of all **the project in nowhere near production ready** so you will probably want to install it only in case you want to help or play with it.

To get it running do the following:

## Prerequisites

1. You probably already have *Maven* installed on your system. If not -- go get it http://mave.apache.org and make sure that `mvn` is in your `$PATH`
2. Web UI componenet requires `npm` and `grunt` to be available in your `$PATH`. See http://www.gruntjs.org/ and http://nodejs.org/ for details

## Installation

1. Project depend on [iron-utils](https://github.com/nyrkovalex/iron-utils) so go there and

  ```bash
  # create some directory on you system
  mkdir iron-stuff
  cd iron-stuff
  
  # clone the repository somewhere
  git clone git@github.com:nyrkovalex/iron-utils.git
  
  # install its maven artifact to local repository
  cd iron-utils
  mvn install
  ```
  
2. Clone this repository and install artifacts

  ```bash
  # do not forget to go back in case you're under `iron-utils` directory
  cd ..
  git clone git@github.com:nyrkovalex/iron-admin.git
  cd iron-admin
  mvn install
  ```

## Run

1. Run the example app starting `Main` class from `org.github.nyrkovalex.ironadmin.example` package adding `../server/target/classes` and `../core/target/classes` to the classpath

  ```bash
  # TODO
  ```
