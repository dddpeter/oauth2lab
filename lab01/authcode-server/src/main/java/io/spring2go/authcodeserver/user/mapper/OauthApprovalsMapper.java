package io.spring2go.authcodeserver.user.mapper;

import io.spring2go.authcodeserver.user.entity.OauthApprovals;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lijinde
 * @since 2022-05-14
 */
@Mapper
public interface OauthApprovalsMapper extends BaseMapper<OauthApprovals> {

}
