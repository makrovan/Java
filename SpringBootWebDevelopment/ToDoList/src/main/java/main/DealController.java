package main;

import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DealController
{
    @Autowired
    private DealRepository dealRepository;
    @RequestMapping(value = "/deals/", method = RequestMethod.GET)
    public List<Deal> listAllDeals(){
        Iterable<Deal> dealIterable = dealRepository.findAll();
        ArrayList<Deal> deals = new ArrayList<>();
        for (Deal deal : dealIterable){
            deals.add(deal);
        }
        return deals;
    }

    @RequestMapping(value = "/deals/", method = RequestMethod.POST)
    public int addDeal(@RequestBody Deal deal){
        Deal newDeal = dealRepository.save(deal);
        return newDeal.getId();
    }

    @RequestMapping(value = "/deals/", method = RequestMethod.DELETE)
    public void deleteAll(){
        dealRepository.deleteAll();
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity<Deal> getDeal(@PathVariable int id){
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (optionalDeal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalDeal.get(), HttpStatus.OK);
    }

    @PostMapping("/deals/{id}")
    public ResponseEntity addDealWithId(@PathVariable int id, @RequestBody(required = false) Deal deal)
    {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/deals/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable int id, @RequestBody Deal deal){
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (optionalDeal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        optionalDeal.get().setName(deal.getName());
        optionalDeal.get().setToDo(deal.getToDo());
        dealRepository.save(optionalDeal.get());
        return new ResponseEntity(optionalDeal.get(), HttpStatus.OK);
    }

    @DeleteMapping("/deals/{id}")
    public ResponseEntity deleteDeal(@PathVariable int id){
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (optionalDeal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        dealRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}