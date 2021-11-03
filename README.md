<div id="top"></div>

[![LinkedIn][linkedin-shield]][linkedin-url]

<br />

<h3 align="center">Customer Application</h3>

  <p align="center">
    A single page application built with Spring boot and ReactJs,
    that lists customers and categorizes them according to country phone numbers,
    and the state of the phone number.
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
		<li><a href="#docker-building">Docker building</a></li>
		<li><a href="#docker-separate-images">Docker separate images</a></li>
		<li><a href="#docker-compose">Docker compose</a></li>
	  </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
[![Product Name Screen Shot][product-screenshot]


### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [React.js](https://reactjs.org/)
* [SQLite](https://www.sqlite.org/index.html)
* [LiquiBase](https://www.liquibase.org/)
* [react-bootstrap-table2](https://react-bootstrap-table.github.io/react-bootstrap-table2/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

before you start the project you need to have java JDK, node.js (v16.3.0), Apache maven, And Docker installed on your device

### Installation


1. Clone the repo
   ```sh
   git clone https://github.com/mabdelmaksood/jumia.exercise.git
   ```
2. In the repo move to backend project
	```sh
	cd jumia.exercise
	```
3. Now that you have moved to the backend project location you can build it with maven
	```sh
	mvn clean install
	```
4. add the sample.db (sqlite database file) to both the project root folder (you are currently in), and to the .\target folder.

5. move to the target folder and start the backend application.
	```sh
	
	java -jar jumia.exercise.jar
	```
6. Move to the front end project
	```sh
	cd ../../customers-ui
	```
7. Install NPM packages
   ```sh
   npm install
   ```
8. Build the project 
   ```sh
   npm run build
   ```
9. Now you can start the front end application
	```sh
	npm start
	```
10. Now you can access the front end from your browser at localhost:3000, also the backend api will be availabe at localhost:8080\api
<p align="right">(<a href="#top">back to top</a>)</p>

### Docker building
### Docker separate images

1. In the repo move to backend project
	```sh
	cd jumia.exercise
	```
2. Now that you have moved to the backend project location you can build the docker image using the provided Docker file
	```sh
	docker build -t jumia-be .
	```

3. after the docker build is done you can run the docker image into a container
	```sh
	docker run -p 8080:8080 jumia-be
	```
6. Move to the front end project
	```sh
	cd ../../customers-ui
	```
7. build the frontend docker image
   ```sh
	docker build -t jumia-fe .  
   ```
8. after the docker build is done you can run the docker image into a container 
   ```sh
   docker run -p 3000:3000 jumia-fe
   ```
9. Now you can access the front end from your browser at localhost:3000, also the backend api will be availabe at localhost:8080\api

### Docker compose

1. In the repo run the following command, it will build both images and start them.
	```sh
	docker-compose up --build   
	```
2. Now you can access the front end from your browser at localhost:3000, also the backend api will be availabe at localhost:8080\api


<!-- USAGE EXAMPLES -->
## Usage

The application is simple:
 -At the top right after the header, the application has a simple form with two inputs for quick customer creation.
 -Then right beneth the create user form, we have a multi select dropdown for the selection of countries and state to view customers from.
 -And at the end we have the actual table that shows the customers corresponding to te selected countries, the headers of the columns have arrows that can be used for sorting, and at the footer of the table you will fing the page size and page number controls.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- Getting Multiselect DropDown Options from backend instead of being hardcoded in front end, to simplify adding new countries and regexes.
- making backend in charge of pagination and sorting instead of frontend.
- Adding an "update customer in place" feature.
- Adding a delete button in each row to enable fast deletion of customers.
- adding Encryption to request body.
- change logging to a rolling log file that changes daily for easier log maintainance.
- User Authentication and user privilages should be integerated.


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Mohamed Abdelmaksood  - mabdelmaksood93@gmail.com

Project Link: [https://github.com/mabdelmaksood/jumia.exercise](https://github.com/mabdelmaksood/jumia.exercise)

<p align="right">(<a href="#top">back to top</a>)</p>

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/mohamed-abdelmaksoud-sw-engineer/
[product-screenshot]:  screenshot.JPG

