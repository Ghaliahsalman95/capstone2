package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.APIResponse;
import com.example.capstone2.Model.MaintenanceRequest;
import com.example.capstone2.Service.MaintenanceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequiredArgsConstructor
@RequestMapping("/api/v1/maintenance-request")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    @GetMapping("get-all-maintenance")
    public List<MaintenanceRequest> getAllmaintenance() {
        return maintenanceRequestService.getall();
    }

    @GetMapping("get-maintenance/{id}")
    public ResponseEntity getmaintenanceById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(maintenanceRequestService.getByID(id));
    }

    @PostMapping("/add-maintenance")
    public ResponseEntity addMaintenanceRequest(@RequestBody @Valid MaintenanceRequest maintenanceRequest, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        maintenanceRequestService.add(maintenanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MaintenanceRequest Sending successfully"));
    }

    @PutMapping("update-maintenance-request/{id}")
    public ResponseEntity updateMaintenanceRequest(@PathVariable Integer id, @RequestBody @Valid MaintenanceRequest maintenanceRequest,Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        maintenanceRequestService.update(id,maintenanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MaintenanceRequest updated successfully"));
    }

    @DeleteMapping("delete-maintenance-request/{id}")
    public ResponseEntity deleteMaintenanceRequest(@PathVariable Integer id) {
        maintenanceRequestService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MaintenanceRequest deleted successfully"));
    }
    //1
    @PutMapping("change-status-request/{id}")
    public ResponseEntity changeStatusRequest(@PathVariable Integer id){
        maintenanceRequestService.changeStatusRequest(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("change successfully"));
    }
    //2

    @GetMapping("give-all-request/{tenantID}/{status}")
    public ResponseEntity giveAllRequest(@PathVariable Integer tenantID,@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(maintenanceRequestService.giveAllRequest(tenantID,status));
    }





}
