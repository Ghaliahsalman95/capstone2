package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.APIResponse;
import com.example.capstone2.Model.Tenant;
import com.example.capstone2.Service.TenantService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant")
public class TenantController {


    private final TenantService tenantService;
//    @Autowired
//    private RestTemplate restTemplate;


    @GetMapping("/get-all-tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("get-by/{id}")
    public ResponseEntity getTenantById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTenantById(id));
    }

    @PostMapping("/add-tenant")
    public ResponseEntity createTenant(@RequestBody @Valid Tenant tenant, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        tenantService.addTenant(tenant);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Tenant added successfully"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateTenant(@PathVariable Integer id, @RequestBody @Valid Tenant tenant, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        tenantService.updateTenant(id, tenant);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Tenant updated successfully"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTenant(@PathVariable Integer id) {
        tenantService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Tenant deleted successfully"));
    }
    //Integer tenantID, Integer propertyId
@PutMapping("rent-property/{tenantID}/{propertyId}")
    public ResponseEntity rentProperty(@PathVariable Integer tenantID, @PathVariable Integer propertyId){
        tenantService.rentProperty(tenantID,propertyId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Successfully rent you can see your lease"));
    }

    //---------------------------------------  5 extra endpoints ------------------------
    //1
    @PostMapping("send-maintenance-request/{tenantID}/{propertyID}")
    public ResponseEntity sendMAinTenanceRequest(@PathVariable Integer tenantID, @PathVariable Integer propertyID, @RequestBody String description) {
        tenantService.sendMAinTenanceRequest(tenantID, propertyID, description);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Send Maintenance request successfully"));
    }

    //2
    @PostMapping("re-send-request/{tenantID}/{mainRequestID}")
    public ResponseEntity reSendRequest(@PathVariable Integer tenantID, @PathVariable Integer mainRequestID) {
      tenantService.reSendRequest(tenantID,mainRequestID);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Re-Send Maintenance request successfully with ID " + mainRequestID));
    }

    //3
    @GetMapping("find-tenants-expired-date")
    public ResponseEntity findTenantsExpiredDate() {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.findTenantsExpiredDate());
    }

    //4

    //5

//
//    //calculateTotalRentdebt
//    //findTenantsExpiredDate(LocalDate startDate, LocalDate endDate) {
//    //6
    @GetMapping("find-tenants-with-expired-lease")
    public ResponseEntity findTenantsWithExpiredLease() {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.findTenantsWithExpiredLease());
    }
//
//    //7
    //findTenantsWithMultipleLeases
    @GetMapping("find-tenants-with-multiple-leases")
    public ResponseEntity findTenantsWithmultipleLeases() {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.findTenantsWithmultipleLeases());

    }



}