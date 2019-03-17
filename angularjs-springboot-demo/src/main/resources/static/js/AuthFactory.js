var app = angular.module("myApp");

app.factory("authFactory",[function(){
     var factoryInstance = {};

     factoryInstance.setAuthenticationRole = function(authenticatedUser){
        factoryInstance.authToken = authenticatedUser;
     }

     factoryInstance.getAuthenticationRole = function(){
        return factoryInstance.authToken;
     }


    return factoryInstance;
}]);
