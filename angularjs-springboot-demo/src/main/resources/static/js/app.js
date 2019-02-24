var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider.
        when("/", {
            templateUrl:"/views/home.html",
            controller:"home"

        }).
        when("/customer/home", {
            templateUrl:"/views/customer-home.html",
            controller:"login"
        }).
        when("/manager/home", {
            templateUrl:"/views/manager-home.html",
            controller:"employee"
        }).
        when("/emplogin", {
            templateUrl:"/views/employee-login.html",
            controller:"login"
        }).
        when("/custlogin", {
            templateUrl:"/views/customer-login.html",
            controller:"login"
        }).
        when("/signup", {
            templateUrl:"/views/customer-signup.html",
            controller:"login"
         }).
        when("/custProfile", {
            templateUrl:"/views/customer-updateprofile.html",
            controller:"updateProfile"
        }).
        when("/regcomplaint", {
            templateUrl:"/views/customer-register-complaint.html",
            controller:"customerComplaints"
        }).
        when("/changepassword", {
             templateUrl:"/views/changepassword.html",
             controller:"updateProfile"
        }).
        when("/trackComplaint", {
             templateUrl:"/views/customer-track-complaint.html",
             controller:"customerComplaints"
        }).
        when("/emp/trackComplaint", {
              templateUrl:"/views/manager-track-Complaint.html",
              controller:"employee"
        }).
        when("/emp/addemp", {
              templateUrl:"/views/manager-employee-add.html",
              controller:"employee"
        }).
        when("/emp/delemp", {
              templateUrl:"/views/manager-employee-delete.html",
              controller:"employee"
        }).
        otherwise({
             redirectTo:"/"
        })

});