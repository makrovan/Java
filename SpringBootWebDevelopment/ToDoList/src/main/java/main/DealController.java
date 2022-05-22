package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Deal;

import java.util.List;

@RestController
public class DealController
{
    @RequestMapping(value = "/deals/", method = RequestMethod.GET)
    public List<Deal> list(){
        return DealStorage.getAllDeals();
    }

    @RequestMapping(value = "/deals/", method = RequestMethod.POST)
    public int add(Deal deal){
        return DealStorage.addDeals(deal);
    }

    @RequestMapping(value = "/deals/", method = RequestMethod.DELETE)
    public void deleteAll(){
        DealStorage.deleteAllDeals();
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity get(@PathVariable int id){
        Deal deal = DealStorage.getDeal(id);
        if (deal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(deal, HttpStatus.OK);
    }

    @PostMapping("/deals/{id}")
    public ResponseEntity denial(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/deals/{id}")
    public ResponseEntity updateDeal(@PathVariable int id, Deal deal){
        if (DealStorage.updateDeal(id, deal)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/deals/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if (DealStorage.deleteDeal(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
