import React from 'react';

import Dropdown from '../../../components/Dropdown/Dropdown';

import { DropdownToggle, Input } from 'reactstrap';

import styles from './ResumeCreate.module.scss';

import { CAREER_DATAS, SCHOOL_TYPE_DATAS, SCHOOL_TYPE_LABELS } from '../constants';
import { Button } from 'reactstrap';

const ResumeBasic = ({isViewInterviewList, isView, basicData, setBasicData}) => {

    /* 드롭다운 */
	const getDropdownItems = (datas, setter, key) => {
		return datas.map(({ title }) => ({
			title,
			onClick: () => setter((prev) => ({ ...prev, [key]: title })),
		}));
	};

	const handleChange = (e) => {
		const {id, value} = e.target;
		setBasicData((prevData) => ({
			...prevData,
			[id]: value,
		}))
	}

	const calcAge =(birth) => {                 

		const date = new Date();
	
		const year = date.getFullYear();
	
		let month = (date.getMonth() + 1);
	
		let day = date.getDate();
	
		if (month < 10) month = '0' + month;
	
		if (day < 10) day = '0' + day;
	
		const monthDay = month + day;
	
	
		const birthdayy = birth.substr(0, 4);
	
		const birthdaymd = birth.substr(4, 4);
	
	 
	
		const age = monthDay < birthdaymd ? year - birthdayy - 1 : year - birthdayy;
	
		return age;
	
	} 

    return (
        <div className='basic'>
			{ isView == false && <div>
				<h4>{basicData.name}</h4>
				<Dropdown
					toggleClassName={styles.DropdownToggle}
					itemClassName={styles.DropdownItem}
					selectItemDatas={getDropdownItems(CAREER_DATAS, setBasicData, 'career')}
					selectedItemText={basicData.career}
					color="outline-dark"
				/>
				<p>{calcAge(basicData.birth)}세</p>
				<p>Email: {basicData.email}</p>
				<p>Phone: {basicData.phone}</p>
				<input 
								id='address'
								onChange={handleChange}
								className={styles.InputRequirements}
								type='text'
								value={basicData.address}
								placeholder='주소를 입력해주세요'
							/>
			</div>}
			{ (isViewInterviewList == true || isView == true) && <div>
				<h4>{basicData.name}</h4>
				<h5>{basicData.career}</h5>
				<p>{calcAge(basicData.birth)}세</p>
				<p>Email: {basicData.email}</p>
				<p>Phone: {basicData.phone}</p>
				<p>Adress: {basicData.address}</p>
			</div>}
		</div>
    )
};

export default ResumeBasic;