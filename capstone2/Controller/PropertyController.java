package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.APIResponse;
import com.example.capstone2.Model.Property;
import com.example.capstone2.Model.Tenant;
import com.example.capstone2.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("get-all-property")
    public List<Property> getAllProperty() {
        return propertyService.getAll();
    }

    @GetMapping("get-property/{id}")
    public ResponseEntity getPropertyById(@PathVariable Integer id) {
    return ResponseEntity.status(HttpStatus.OK).body(propertyService.getByID(id));
    }

    @PostMapping("/add-property")
    public ResponseEntity addProperty(@RequestBody @Valid Property property, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        propertyService.add(property);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Property added successfully"));
    }

    @PutMapping("update-property/{id}")
    public ResponseEntity updateProperty(@PathVariable Integer id, @RequestBody @Valid Property property,Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       propertyService.update(id,property);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Property updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProperty(@PathVariable Integer id) {
     propertyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Property deleted successfully"));
    }

    //--------------------------- 6 extra endpoints -------------------------
    //1
    @GetMapping("get-calculate-average-rent-price")
    public ResponseEntity calculateAverageRentPrice(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.calculateAverageRentPrice());
    }
    //2
    @GetMapping("get-average-rent-for-each-type")
    public ResponseEntity AverageRenforechaType(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.AverageRenforechaType());
    }
    //3
    @GetMapping("find-free-property")
    public ResponseEntity findfreeProperties(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findfreeProperties());
    }
    //4
    @GetMapping("get-pending-requests")
    public ResponseEntity getPendingRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPendingRequests());
    }
    //4
    @GetMapping("get-short-leases")
    public ResponseEntity getshhortLeases(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getshhortLeases());
    }
    //5
    @GetMapping("get-total-revenue-each-property/{startDate}/{endDate}")
    public ResponseEntity calculateTotalRevenueByProperty(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.calculateTotalRevenueByProperty(startDate,endDate));
    }


}
