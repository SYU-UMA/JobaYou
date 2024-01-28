import React from 'react';

import styles from './MyPageNavigation.module.scss';

import { Link, useNavigate } from "react-router-dom";

const MyPageNavigation = () => {
	const navigate = useNavigate();
  
    const movePage = (url) => {
      navigate(url)
    };

	return (
			<div className={styles.Header}>
				<div >
                    <Link to={'/myPage'}>마이페이지</Link>
					<Link to={'/resumeManage'}>이력서 관리</Link>
				</div>
			</div>
	);
};

export default MyPageNavigation;
