var app = angular.module("myApp");

app.controller("employee", function ($scope, $rootScope, $http, $location) {

         $scope.employeeData={};
         $scope.message;
         $scope.dashboard={};
         $scope.selectedData = [];
         $scope.selectedEmp="1";
         $scope.loadEmployees = function() {
                 var curl = '/employees/loadAll/'+$rootScope.customerId;
                     $http({
                         method: 'GET',
                         url: curl,
                         data: '',
                         headers: {
                              'Content-Type': 'application/json'
                             }
                         }).success(function (response) {
                                   $scope.employees = response;
                         })
                           .error(function (data, status, header, config) {
                         });
             };

         $scope.addEmployee = function () {
                            var response;
                     $scope.employeeData.userName = $rootScope.userName;
                     $http.post('/employees/add', $scope.employeeData)
                               .success(function (response) {
                                   if(response.messageSuccess === "Employee Added Successfully"){
                                      $scope.message=response.messageSuccess;

                                    }else{
                                        $location.path('/');
                                    }

                               })
                               .error(function (response, status, header, config) {
                          });
                  };

         $scope.delete = function(employeeId) {
                var url = '/employees/delete'+"/"+employeeId+"/"+$rootScope.customerId;
                    $http({
                        method: 'POST',
                        url: url,
                        data: '',
                        headers: {
                           'Content-Type': 'application/json'
                           }
                        })
                    .success(function (response) {
                                 if(response){
                                    $scope.message="Employee Deleted Successfully"
                                    $scope.employees = response;
                                  }else{
                                      $scope.message=response.messageError;
                                  }

                                  })
                                  .error(function (response, status, header, config) {
                          });
         };


         $scope.loadComplaints = function() {
                         var curl = '/employees/dashboard/'+$rootScope.customerId;
                             $http({
                                 method: 'GET',
                                 url: curl,
                                 data: '',
                                 headers: {
                                      'Content-Type': 'application/json'
                                     }
                                 }).success(function (response) {
                                           response.complaints.forEach(function(obj) { obj.selected = false; });
                                           $scope.dashboard=response;
                                 })
                                   .error(function (data, status, header, config) {
                                 });
                     };

          $scope.filter_selected = function(event) {
              return event.selected == true;
          }

          $scope.changeSelected = function(data){
               if(!data.selected){
                data.selected = true;
               }else {
                data.selected = false;
               }
          }

        $scope.submitData = function() {
                    var url ='/employees/complainst/assigneto';
                     var filteredArray = $scope.dashboard.complaints.filter(function(item){
                       return item.selected === true;
                     });
                      var complaintsSelected ={};
                      complaintsSelected.complaints = filteredArray;
                      complaintsSelected.assignedToEmployeeId = $scope.selectedEmp;
                      $http({
                          method: 'POST',
                          url: url,
                          data: complaintsSelected,
                          headers: {
                             'Content-Type': 'application/json'
                             }
                          }).success(function (response) {
                             $scope.dashboard=response;
                             $scope.message = "Assigned to Employee!";
                          })
                             .error(function (data, status, header, config) {
                          });

                     console.log(filteredArray);
        }



});