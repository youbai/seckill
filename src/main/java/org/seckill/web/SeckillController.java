package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckllResult;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016/6/11.
 * Restful接口设计
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.POST)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckllResult<Exposer> exposer(Long seckiillId){
        return null;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckllResult<SeckillExecution> excute(@PathVariable("seckillId") Long seckiillId,
                                                 @PathVariable("md5") String md5,
                                                 @CookieValue(value = "killPhone", required = false) Long phone){
        return null;
    }

}
