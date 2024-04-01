package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.APIResponse;
import com.example.capstone2.Model.Invoice;
import com.example.capstone2.Service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    
    private final InvoiceService invoiceService;

    @GetMapping("get-all-invoice")
    public List<Invoice> getAll() {
        return invoiceService.getall();
    }



    @PostMapping("/add-invoice")
    public ResponseEntity add(@RequestBody @Valid Invoice invoice, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        invoiceService.add(invoice);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Invoice add successfully"));
    }

    @PutMapping("update-invoice-/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Invoice invoice,Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        invoiceService.update(id,invoice);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Invoice updated successfully"));
    }

    @DeleteMapping("delete-invoice/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        invoiceService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Invoice deleted successfully"));
    }
@GetMapping("get-status/{status}")
    public ResponseEntity getStatus(@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getStatus(status));
}
    @GetMapping("delete-invoice/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getOne(id));

    }

    @GetMapping("get-overdue")
    public ResponseEntity overdue() {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.overdue());
    }

    @GetMapping("/get-calculateTotal-rent-debt/{status}")
    public ResponseEntity debt(@PathVariable String status) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.calculateTotalRentdebt(status));
    }
    @PutMapping("change-status-invoice/{id}")
    public ResponseEntity changeStatusPAY(@PathVariable Integer id){
        invoiceService.changeStatusPAY(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("change successfully"));
    }

    @PutMapping("change-status-invoice-partial/{id}")
    public ResponseEntity changeStatusPARTIAL(@PathVariable Integer id){
        invoiceService.changePARTIALLY_PAID(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("change successfully"));
    }

    //calculateTotalRevenueByStatus
    //changeStatusRequest
    //1
@GetMapping("calculate-total-revenue/{startDate}/{endDate}/{status}")
private ResponseEntity calculateTotalRevenueByStatus(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate ,@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.calculateTotalRevenueByStatus(startDate,endDate,status));
}
}
