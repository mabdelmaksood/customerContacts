<div id="top"></div>

[![LinkedIn][linkedin-shield]][https://www.linkedin.com/in/mohamed-abdelmaksoud-sw-engineer/]


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
		<li><a href="#docker-building">Installation</a></li>
		<li><a href="#docker-separate-images">Prerequisites</a></li>
		<li><a href="#docker-compose">Installation</a></li>
	  </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project



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

before you start the project you need to have node.js (v16.3.0) and Apache maven installed on your device

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
10. Now you can access the front end from your browser at localhost:3030, also the backend api will be availabe at localhost:8080\api
<p align="right">(<a href="#top">back to top</a>)</p>

### Docker building
###Docker separate images

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
9. Now you can access the front end from your browser at localhost:3030, also the backend api will be availabe at localhost:8080\api

###Docker compose

1. In the repo run the following command, it will build both images and start them.
	```sh
	docker-compose up --build   
	```
2. Now you can access the front end from your browser at localhost:3030, also the backend api will be availabe at localhost:8080\api


<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [] Feature 1
- [] Feature 2
- [] Feature 3
    - [] Nested Feature

See the [open issues](https://github.com/mabdelmaksood/jumia.exercise/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Mohamed Abdelmaksood  - mabdelmaksood93@gmail.com

Project Link: [https://github.com/mabdelmaksood/jumia.exercise](https://github.com/mabdelmaksood/jumia.exercise)

<p align="right">(<a href="#top">back to top</a>)</p>




