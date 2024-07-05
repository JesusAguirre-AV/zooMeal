##Project desciption 
Final project for backend course that applies JPAs using ports to connect to a database as well as a REST application. This is used to simulate a zoo's meal planner for the animals with tables for the species, classification, and diet details
##Technologies used
This is an implementation of JPAs, Spring Boot, Java, RESTful API, SQL, and is generaly equiped to interact with an SQL database.
##Favorite features 
The usage of different applications such as MySQL, Eclipse(IDE used), DBeaver, ARC(RESTfull API) used to communicated between each other.
##Code snippet
@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public SpeciesClassification postingClassification(
			@RequestBody SpeciesClassification sClassification) {
		log.info("Creating classification {}", sClassification);
		return zooMealService.saveClassification(sClassification);
	}
 (This is the method that instructs Spring Boot on how to fill a row in the classification table)
##Installation and usage
Other than pulling it from the git repository, it requires spring boot and maven applications. It is also designed to run allong a RESTful API to be able to access its database.
##Contribution section //Here there can be requests as well as feedback and possible bug details

##Contact thebroness619@gmail.com
