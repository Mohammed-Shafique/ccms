var app = angular.module("myApp");

app.controller("home", ['$scope', '$rootScope', '$http','$location', function($scope, $rootScope, $http, $location) {

   $scope.logout = function(){
         var data={
                 email:$rootScope.userName
                 }
             $http.post('/logout', data)
                 .success(function (response) {
                     if(response.loginStatus === false){
                           $rootScope.accountCreatedMessage = undefined;
                           $rootScope.accountCreated=false;
                           $rootScope.loginStatus =false;
                           $rootScope.userName=undefined;
                           $rootScope.isCustomer =false;
                           $rootScope.customerId = -1;
                           $rootScope.isCustomerLoggedIn=false;
                           $rootScope.isManagerLoggedIn= false;
                           $location.path('/');
                          }else{

                            }
                              $location.path('http://localhost:8080');
                          })
                        .error(function (registrationFormData, status, header, config) {
                 });
   }
}]);