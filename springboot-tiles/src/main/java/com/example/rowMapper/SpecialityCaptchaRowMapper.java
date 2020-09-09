package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.SpecialityCaptchaDTO;

@Component
public class SpecialityCaptchaRowMapper implements RowMapper<SpecialityCaptchaDTO> {

	public SpecialityCaptchaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final SpecialityCaptchaDTO specialityCaptchaDTO = new SpecialityCaptchaDTO();
		specialityCaptchaDTO.setId(rs.getLong("id"));
		specialityCaptchaDTO.setCaptchaText(rs.getString("captcha_txt"));
		specialityCaptchaDTO.setCaptchaImagePath(rs.getString("captcha_img_url"));
		specialityCaptchaDTO.setSpeciality(rs.getLong("speciality_id"));
		specialityCaptchaDTO.setSpecialityName(rs.getString("specialityName"));
		return specialityCaptchaDTO;
	}

}