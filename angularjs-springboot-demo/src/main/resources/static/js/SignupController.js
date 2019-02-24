var app = angular.module("myApp");

app.controller("signup", function ($scope, $http, $location) {

        $scope.signup = function () {
                // add more fields here as per input fields in
                // signup page
                   var data = {
                       firstName: $scope.firstName,
                       lastName: $scope.lastName
                   };


                   $http.post('/signup', data)
                   .success(function (data) {
                       if(data.loginStatus === true){
                           $location.path('/home');
                        }else{
                           $location.path('/');
                        }
                       //$scope.PostDataResponse = data;
                   })
                   .error(function (data, status, header, config) {
                   });
               };



           });

