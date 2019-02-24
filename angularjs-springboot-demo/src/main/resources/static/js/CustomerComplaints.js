var app = angular.module("myApp");

app.controller("customerComplaints", function ($scope, $rootScope, $http, $location) {

    $scope.customerComplaints ={};
    $scope.isRegistered=false;
    $scope.complntRegisredMessage;
    $scope.registeredMessage;
    $scope.searchComplainId;

    $scope.products = ["Laptop", "Desktop", "Mobile Phone"];
    $scope.prdCategory = ["Screen", "Keypad/Keyboard", "Charger", "Internal Disk"];

    var registration = {};
    $scope.complaints =[];
    $scope.loadCustomerComplaints= function() {
        var curl = '/customers/complaints/'+$rootScope.customerId;
            $http({
                method: 'GET',
                url: curl,
                data: '',
                headers: {
                     'Content-Type': 'application/json'
                    }
                }).success(function (response) {
                          $scope.complaints = response;
                })
                  .error(function (data, status, header, config) {
                });
    };

    $scope.searchComplaints= function() {
            var curl = '/customers/complaints/'+$rootScope.customerId+"/"+$scope.searchComplainId;
                $http({
                    method: 'POST',
                    url: curl,
                    data: '',
                    headers: {
                         'Content-Type': 'application/json'
                        }
                    }).success(function (response) {
                              $scope.complaints = response;
                    })
                      .error(function (data, status, header, config) {
                    });
        };


    $scope.registerComplaints = function(){
        registration.customerId=$rootScope.customerId;
        registration.productName=$scope.product;
        registration.productCategory=$scope.category;
        registration.complaint=$scope.complaint;
         var url = '/customers/complaints';
             $http({
                method: 'POST',
                 url: url,
                 data: registration,
                 headers: {
                      'Content-Type': 'application/json'
                 }
                 }).success(function (response) {
                       if(response.messageSuccess){
                        $scope.isRegistered=true;
                        $scope.registeredMessage=response.messageSuccess;
                       }
                     })
                     .error(function (customerProfile, status, header, config) {

                      });
    };


});