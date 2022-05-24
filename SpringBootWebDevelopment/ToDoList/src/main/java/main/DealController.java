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
    public List<Deal> listAllDeals(){
        return DealStorage.getAllDeals();
    }

    @PostMapping("/deals/")
    public int addDeal(@RequestBody Deal deal){
        return DealStorage.addDeals(deal);
    }

    @RequestMapping(value = "/deals/", method = RequestMethod.DELETE)
    public void deleteAllDeals(){
        DealStorage.deleteAllDeals();
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity<Deal> getDeal(@PathVariable int id){
        Deal deal = DealStorage.getDeal(id);
        if (deal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(deal, HttpStatus.OK);
    }

    @PostMapping("/deals/{id}")
    public ResponseEntity addDealWithId(@PathVariable int id, @RequestBody(required = false) Deal deal)
    {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/deals/{id}")
    public ResponseEntity updateDeal(@PathVariable int id, @RequestBody Deal deal){
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
