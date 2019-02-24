var app = angular.module("myApp");

app.controller("updateProfile", function ($scope, $rootScope, $http, $location) {


    $scope.customerProfile ={};
    $scope.profileUpdateMessage;
    $scope.passwordChangeMessage;
    $scope.isupdateSuccess = false;
    $scope.isPasswordChangeSuccess = false;
            var url = '/customers/'+$rootScope.customerId;

    $scope.loadCustomer= function(parameter) {
             console.log(parameter);
             if(parameter === 'Y')
                {
                url=url+'/Y';
                }else{
                    url =url+'/N'
                }

               $http({
                    method: 'GET',
                    url: url,
                    data: '',
                    headers: {
                        'Content-Type': 'application/json'
                        }
                    }).success(function (response) {
                      $scope.customerProfile = response;
                    })
                    .error(function (customerProfile, status, header, config) {
                    });
             };

    $scope.updateCustomerProfile= function() {
         console.log("update called");
             $http({
                method: 'POST',
                 url: '/customers/profile/update',
                 data: $scope.customerProfile,
                 headers: {
                      'Content-Type': 'application/json'
                 }
                 }).success(function (response) {
                       $scope.profileUpdateMessage = response.messageSuccess;
                       if(response.messageSuccess){
                        $scope.isupdateSuccess=true;
                       }
                     })
                     .error(function (customerProfile, status, header, config) {

                      });
    };

    $scope.changePassword= function() {
                 $http({
                    method: 'POST',
                     url: '/customers/password/change',
                     data: $scope.customerProfile,
                     headers: {
                          'Content-Type': 'application/json'
                     }
                     }).success(function (response) {
                           $scope.passwordChangeMessage = response.messageSuccess;
                           if(response.messageSuccess){
                            $scope.isPasswordChangeSuccess=true;
                           }
                         })
                         .error(function (customerProfile, status, header, config) {

                          });
        };



});
