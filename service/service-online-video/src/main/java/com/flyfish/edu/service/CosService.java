package com.flyfish.edu.service;

import com.flyfish.edu.R.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @autohr flyfish
 * @date: 2023/5/15 17:43
 * @description:
 */
public interface CosService {
    String upload(MultipartFile file);
}
