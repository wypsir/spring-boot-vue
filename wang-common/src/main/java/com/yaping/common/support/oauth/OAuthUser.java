package com.yaping.common.support.oauth;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.yaping.common.entity.user.SysUser;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyp
 * @since 2017-10-01
 */
@TableName("o_auth_user")
public class OAuthUser extends Model<OAuthUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("oAuth_type")
	private String oAuthType;
	@TableField("oAuth_id")
	private String oAuthId;

	@TableField(exist = false)
	private SysUser user;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getoAuthType() {
		return oAuthType;
	}

	public void setoAuthType(String oAuthType) {
		this.oAuthType = oAuthType;
	}

	public String getoAuthId() {
		return oAuthId;
	}

	public void setoAuthId(String oAuthId) {
		this.oAuthId = oAuthId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
}
