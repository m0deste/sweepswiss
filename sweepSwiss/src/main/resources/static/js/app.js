/**
 * 
 */

var app=angular.module("MyCat", []);
app.controller("CatController", function($scope, $http){
	
	$scope.produits=[];
	$scope.motCle=null;
	$scope.pageCourante=0;
	
	//Liste produits par mot clé
	$scope.charger=function(){
		$http.get("/productByKW?mc="+$scope.motCle+"&page="+$scope.pageCourante+"")
	    .then(function(response){
	     $scope.produits=response.data;
	     //console.log(" Liste des clients "+data); //
	    }) ;
	};
	
	//Liste complète de produits
	$http.get("/all")
    .then(function(response){
     $scope.produits=response.data;
     //console.log(" Liste des clients "+data); //
    }) ;
			
});