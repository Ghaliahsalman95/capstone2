package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.APIResponse;
import com.example.capstone2.Model.Lease;
import com.example.capstone2.Service.LeaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController@RequestMapping("/api/v1/lease")
public class LeaseController {

    private final LeaseService leaseService;
    @GetMapping("get-all")
    public List<Lease> getAll() {
        return leaseService.getall();
    }

    @GetMapping("get-lease/{id}")
    public ResponseEntity getLeaseById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(leaseService.getByID(id));
    }

    @PostMapping("/add-lease")
    public ResponseEntity add(@RequestBody @Valid Lease lease, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        leaseService.add(lease);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Lease Sending successfully"));
    }

    @PutMapping("update-lease/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Lease lease,Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        leaseService.update(id,lease);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Lease updated successfully"));
    }

    @DeleteMapping("delete-lease/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        leaseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Lease deleted successfully"));
    }
    @GetMapping("get-lease/{tenantId}")
    public ResponseEntity getLeaseByTenantId(@PathVariable Integer tenantId) {
        return ResponseEntity.status(HttpStatus.OK).body(leaseService.findLeasesByTenantId(tenantId));
    }
    //1
    @GetMapping("expired-leases-list")
    public ResponseEntity expiredLeasesList(){
        return ResponseEntity.status(HttpStatus.OK).body(leaseService.expiredLeasesList());
    }
//2
    @PostMapping("expired-leases")
    public ResponseEntity expiredLeases(){
        leaseService.expiredLeases();
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("expiredLeases Successfully"));
    }
    //3

}
