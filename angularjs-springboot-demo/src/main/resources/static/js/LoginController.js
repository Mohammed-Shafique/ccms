var app = angular.module("myApp");

app.controller("login", function ($scope, $rootScope, $http, $location, authFactory) {
        $scope.message
        $scope.userName;
        $scope.accountCreated;
        $scope.accountCreatedMessage;
        $scope.isCustomerLoggedIn=false;
        $scope.isEmployee;
        $scope.isManagerLoggedIn= false;
        $scope.customerId;
        $scope.isLoginfailed = false;
        $scope.loginFormData = {
              email: '',
              password : ''
        };
        $scope.registrationFormData = {
                firstName:'',
                lastName:'',
                email:'',
                phone:'',
                address1:'',
                address2:'',
                password:'',
                confirmPassword:''
        };
        $scope.login = function (parameter) {
            $rootScope.accountCreatedMessage =undefined;
            $scope.loginFormData.userType = parameter;
            var data ={};
            data.email = $scope.formData.email;
            data.password = $scope.formData.password;
            data.userType = parameter;
            $http({
                  method: 'POST',
                  url: '/login',
                  data: data,
                  headers: {
                      'Content-Type': 'application/json'
                   }
             })
            .success(function (loginFormData) {

                if(loginFormData.loginStatus === true){

                    $scope.message = loginFormData.message;
                    $rootScope.userName=loginFormData.userName;
                    $rootScope.loginStatus =loginFormData.loginStatus;
                    $rootScope.customerId = loginFormData.customerId;
                        //route Authentication code
                     authFactory.setAuthenticationRole(loginFormData.customerId);
                         //route Authentication code
                    if(loginFormData.empType === 'CUST'){
                        $rootScope.isCustomerLoggedIn = true;
                        $location.path('/customer/home');
                    }else if(loginFormData.empType === 'MAN'){
                        $rootScope.isManagerLoggedIn=true;
                        $location.path('/manager/home');
                    }
                 }else{
                    $scope.message=loginFormData.message;
                    $scope.isLoginfailed = true;
                 }
            })
            .error(function (loginFormData, status, header, config) {
            });
        };
    $scope.signup = function () {
                    var response;
             $http.post('/signup', $scope.registrationFormData)
                       .success(function (response) {
                           if(response.loginStatus === true){
                              $rootScope.accountCreatedMessage = "Congratulation! Account Created Successfully. Please login";
                              $rootScope.accountCreated=true;
                              $rootScope.loginStatus =false;
                              $rootScope.userName=undefined;
                              $rootScope.isCustomerLoggedIn = false;
                              $rootScope.isManagerLoggedIn=false;
                            }else{
                                $location.path('/');
                            }

                       })
                       .error(function (response, status, header, config) {
                  });
          };



});

