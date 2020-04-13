package com.firstspring.testspring.controller;

import com.firstspring.testspring.data.TvSeries;
import com.firstspring.testspring.services.TvSeriesSevice;
import com.firstspring.testspring.data.tvCharacterDto;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.*;


@RestController
@RequestMapping("tvSeries")
public class tvSeriesControllor {
    @Autowired
    TvSeriesSevice tvSeriesSevice;
    /**
     * 测试GetMapping
     * @return
     */
    @GetMapping
    public List<TvSeries> getAll() {
        List<TvSeries> list = tvSeriesSevice.getAllTvSeries();
        return list;
    }
    //测试PathVariable
    @GetMapping("/{id}")
    public TvSeries getOne(@PathVariable int id) {
        if(id == 100) {
            return createPoi(id);
        }else if(id == 102) {
            return createWest();
        }else {
            throw new RuntimeException();
        }
    }
    //测试PostMapping和RequestBody和RequestParam
    //如果不写@Valid注解 前面的设置的一切校验都没用
    @PostMapping
    public String insertOne(@Valid @RequestBody TvSeries tvSeries,
                              @RequestParam("name") String name
                                 ) {
        int status = tvSeriesSevice.insertOne(tvSeries);
        return "{\"status\"," + "\"" + status + "\"";
    }
    //测试PutMapping
    @PutMapping("/{id}")
    public TvSeries updateOne(@PathVariable int id) {
        int updateId = tvSeriesSevice.updateOne(id);
        return createPoi(updateId);

    }
    //测试DeleteMapping
    @DeleteMapping("/{id}")
    public Map<String, String> deleteOne(@PathVariable int id,
                                         @RequestParam(value = "delete_reson") String delete_reson,
                                         HttpServletRequest httpServletRequest){
        Map<String, String> result = new HashMap<>();
        if(id == 101) {
            int delId = tvSeriesSevice.deleteOne(id);
            result.put("msg", delId + httpServletRequest.getRemoteAddr() + "删除（原因：）" + delete_reson);
        }else if(id == 102) {
            throw new RuntimeException("#102不能被删除");
        }else {
            throw new RuntimeException();
        }
        return result;
    }

    //测试上传文件
    //consumes 是客户端请求的类型
    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo")MultipartFile multipartFile) throws Exception {
        //保存文件
        FileOutputStream fileOutputStream = new FileOutputStream(multipartFile.getOriginalFilename());
        IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
    }

    //测试服务器返回图片
    //produces 是服务器返回的类型
    @GetMapping(value = "/{id}/photos", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] returnPhoto(@PathVariable int id) throws Exception {
        File file = new File("timg (1).jpg");
        byte[] fileByte = Files.readAllBytes(file.toPath());
        return fileByte;

    }


    public TvSeries createPoi(int id) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 5, 2, 0, 0, 0);
        List<tvCharacterDto> tvCharacterDtos = new ArrayList<>();
        tvCharacterDtos.add(new tvCharacterDto());
        return new TvSeries(id, "Speed And Passion", 8, calendar.getTime());
    }
    public TvSeries createWest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 4, 2, 0, 0, 0);
        List<tvCharacterDto> tvCharacterDtos = new ArrayList<>();
        tvCharacterDtos.add(new tvCharacterDto());
        return new TvSeries(102, "WestWorld", 5, calendar.getTime());
    }
}
