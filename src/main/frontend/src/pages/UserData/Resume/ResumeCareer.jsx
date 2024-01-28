import React, { useEffect } from 'react';

import Dropdown from '../../../components/Dropdown/Dropdown';

import { DropdownToggle, Input } from 'reactstrap';

import styles from './ResumeCreate.module.scss';

import { CAREER_RETIRE_DATAS, CAREER_CURRENCY_DATAS, CAREER_AREA_DATAS} from '../constants';
import { Button } from 'reactstrap';
import AxiosInstance from '../../../api/AxiosInstance';

const ResumeCareer = ({isViewInterviewList, isView, deleteCareer, modifyCareer, addCareer, careerDynamicArray, setCareerDynamicArray}) => {

	/* 드롭다운 */
	const getDropdownItems = (datas, setter, key) => {
		return datas.map(({ title }) => ({
			title,
			onClick: () => setter((prev) => ({ ...prev, [key]: title })),
		}));
	};

	const [isAddOn ,setIsAddOn] = React.useState({
		career: false,
		salary: false,
		area: false,
		edit: false,
		edtiingIndex: null,
	});

	const [careerData, setCareerData] = React.useState({
		companyName: '',
		retire: '재직여부',
		careerStart: '',
		careerEnd: '',
		jobGradeDuties: '',
		deptName: '',
		contents: '',
		salary: '',
		area: '근무지역',
		currency: '금액단위',
	})

	const cancleButton = () => {
		setIsAddOn(prev => ({
			...prev,
			career: false,
			salary: false,
			edit: false,
			edtiingIndex: null,
		}))
		setCareerData({
			companyName: '',
			retire: '재직여부',
			careerStart: '',
			careerEnd: '',
			jobGradeDuties: '',
			deptName: '',
			contents: '',
			salary: '',
			area: '근무지역',
			currency: '금액단위',
		});
	};

	const handleChange = (e) => {
		const {id, value} = e.target;
		setCareerData((prevData) => ({
			...prevData,
			[id]: value,
		}))
	}

	const submitButton = () => {
		addCareer(careerData);

		cancleButton();
	}

	const editButton = (index) => {
		modifyCareer(index, careerData);

		cancleButton();
	}

	const editItem = (index, careerNum) => {
		const selectedCareerData = careerDynamicArray[index];

		setCareerData(selectedCareerData);

		setIsAddOn((prev) => ({
			...prev,
			edit: true,
			edtiingIndex: careerNum,
		}))
	}

	const deleteItem = (index) => {
		deleteCareer(index);
	}

	return (
		<div className='career'>
			<h4>경력</h4>
			{ isView == false && <Button onClick={() => setIsAddOn(prev => ({
				...prev, career: true }))}>추가</Button>}
			<hr/>
			{ (isAddOn.career == false && isAddOn.edit == false)  && <div>
				{  isView == false && careerDynamicArray.length < 1 && <div>
					<h7>경력사항 또는 인턴, 현장실습 등 급여를 받은 업무경험이 있다면 자유롭게 작성해보세요!</h7>
				</div>}
				{ careerDynamicArray.length > 0 && <div>
					<div>
						{careerDynamicArray.map((item, index) => (
							<div key={index}>
								<h6>{item.companyName}</h6> <p>{item.careerStart}~{item.careerEnd}</p>
								<p>{item.deptName} {item.jobGradeDuties}</p>
								<pre>{item.contents}</pre>
								<p>{ item.salary != '' && ("연봉 " + item.salary+item.currency)}</p>
								<p>{ item.area != '' && ("근무지역 " + item.area)}</p>
								{ (isView == false || isViewInterviewList == false) && <div>
									<Button onClick={() => editItem(index, item.careerNum)}>편집</Button>
									<Button onClick={() => deleteItem(item.careerNum)}>삭제</Button>
								</div>}
							</div>
						))}
					</div>
				</div>}
			</div>}
			{ (isAddOn.career == true || isAddOn.edit) && <div className={styles.Div}>
				<div className={styles.Row}>
					<input id='companyName' onChange={handleChange} value={careerData.companyName} type='text' placeholder='회사명*'/>
					<Dropdown
						toggleClassName={styles/DropdownToggle}
						itemClassName={styles.DropdownItem}
						selectItemDatas={getDropdownItems(CAREER_RETIRE_DATAS, setCareerData, 'retire')}
						selectedItemText={careerData.retire}
						color="outline-dark"
					/>
				</div>
				<div className={styles.Row}>
					<input id='careerStart' onChange={handleChange} value={careerData.careerStart} type='text' placeholder='입사년월*'/>
					<input id='careerEnd' onChange={handleChange} value={careerData.careerEnd} type='text' placeholder='퇴사년월*'/>
				</div>
				<div className={styles.Row}>
					<input id='jobGradeDuties' onChange={handleChange} value={careerData.jobGradeDuties} type='text' placeholder='직급/직책'/>
					<input id='deptName' onChange={handleChange} value={careerData.deptName} type='text' placeholder='근무부서'/>
				</div>
				<div className={styles.Row}>
					<h7>담당업무</h7>
					<input type='textarea' id='contents' onChange={handleChange} value={careerData.contents} className={styles.InputRequirements2}
						   placeholder='담당업무를 입력해주세요
						-진행한 업무를 다 적기 보다는 경력사항 별로 중요한 내용만 엄섬해서 작성하는 것이 중요합니다!
						-담당한 업무 내용을 요약해서 작성해보세요!
						-경력별 프로젝트 내용을 적을 경우, 역할/팀구성/기여도/성과를 기준으로 요약해서 작성해보세요!
						'/>
				</div>
				{ isAddOn.salary == true && <div className={styles.Row}>
					<input id='salary' onChange={handleChange} value={careerData.salary} type='text' placeholder='연봉'/>
					<Dropdown
						toggleClassName={styles/DropdownToggle}
						itemClassName={styles.DropdownItem}
						selectItemDatas={getDropdownItems(CAREER_CURRENCY_DATAS, setCareerData, 'currency')}
						selectedItemText={careerData.currency}
						color="outline-dark"
					/>
				</div>}
				{ isAddOn.area == true && <div>
					<Dropdown
						toggleClassName={styles/DropdownToggle}
						itemClassName={styles.DropdownItem}
						selectItemDatas={getDropdownItems(CAREER_AREA_DATAS, setCareerData, 'area')}
						selectedItemText={careerData.area}
						color="outline-dark"
					/>
				</div>}

				<div>
					<Button onClick={() => setIsAddOn(prev => ({
						...prev, salary: !isAddOn.salary }))} >연봉</Button>
					<Button onClick={() => setIsAddOn(prev => ({
						...prev, area: !isAddOn.area }))} >근무지역</Button>
				</div>
				{ isAddOn.career && <div>
					<Button onClick={() => cancleButton()} >취소</Button>
					<Button onClick={() => submitButton()}>저장</Button>
				</div>}
				{ isAddOn.edit && <div>
					<Button onClick={() => cancleButton()} >취소</Button>
					<Button onClick={() => editButton(isAddOn.edtiingIndex)}>편집</Button>
				</div>}
			</div>}
		</div>
	)
};

export default ResumeCareer;