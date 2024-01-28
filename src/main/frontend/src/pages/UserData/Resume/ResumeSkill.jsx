import React from 'react';

import Dropdown from '../../../components/Dropdown/Dropdown';

import { DropdownToggle, Input } from 'reactstrap';

import styles from './ResumeCreate.module.scss';

import { CAREER_DATAS, SCHOOL_TYPE_DATAS, SCHOOL_TYPE_LABELS } from '../constants';
import { Button } from 'reactstrap';

const ResumeSkill = ({isViewInterviewList, isView, deleteSkill, addSkill, skillDynamicArray, setSkillDynamicArray}) => {

	const [isAddOn ,setIsAddOn] = React.useState(false);

	const [inputValue, setInputValue] = React.useState('');
	
	const [tempSkillDynamicArray, setTempSkillDynamicArray] = React.useState([]);

	const handleInputChange = (e) => {
		setInputValue(e.target.value);
	};

	const handleKeyPress = (e) => {
		if (e.key === 'Enter') {
			handleAddToArray();
		}
	};

	const handleAddToArray = () => {
		if (!inputValue) {
			return;
		}

		setTempSkillDynamicArray((prevArray) => {
			const newArray = [...prevArray, {skill: inputValue}];
			return newArray;
		});

		setInputValue('');
	};

	const deleteItem = (index) => {
		setTempSkillDynamicArray((prevArray) => {
			const newArray = [...prevArray];
			newArray.splice(index, 1);
			return newArray;
		});
	}

	const editButton = () => {
		setTempSkillDynamicArray(skillDynamicArray);
		setIsAddOn(true);
	}

	const cancleButton = () => {
		setIsAddOn(false);
		setInputValue('');
		setTempSkillDynamicArray([]);
	};

	const submitButton = () => {

		const addedItems = tempSkillDynamicArray.filter(item => !skillDynamicArray.includes(item));
		const deletedItems = skillDynamicArray.filter(item => !tempSkillDynamicArray.includes(item));

		if(addedItems.length > 0) {
			for(let i = 0; i < addedItems.length; i++) {
				addSkill(addedItems[i].skill);
			}
		}
		if(deletedItems.length > 0) {
			for(let i = 0; i < deletedItems.length; i++) {
				deleteSkill(deletedItems[i].skillNum);
			}
		}

		
		setInputValue('');
		setIsAddOn(false);
	};

    return (
        <div className='skill'>
					<h4>스킬</h4>
					{ (isViewInterviewList == false || isView == false) && <Button onClick={() => editButton()}>편집</Button>}
					<hr/>
					{  (isViewInterviewList == true || isView == true || isAddOn == false) && <div>
						{ (isViewInterviewList == false || isView == false) && skillDynamicArray.length < 1 && <h7>스킬을 추가해주세요</h7>}
						{ skillDynamicArray.length > 0 && <div>
							<div>
								{skillDynamicArray.map((item, index) => (
									<li key={index}>{item.skill}</li>
								))}
							</div>
						</div>}
					</div>}
					{ (isViewInterviewList == false || isView == false) && isAddOn == true && <div>
						<input 
								id='inputSkill'
								className={styles.InputRequirements}
								type='text'
								placeholder='툴/직무역량/소프트스킬을 입력 후 엔터를 눌어주세요'
								value={inputValue}
								onChange={handleInputChange}
								onKeyPress={handleKeyPress}
						/>
						<div>
							<h7>나의 스킬</h7>
							<div>
								{tempSkillDynamicArray.map((item, index) => (
									<li onClick={() => deleteItem(index, item.skillNum)} key={index}>{item.skill}</li>
								))}
							</div>
						</div>
						<div>
							<Button onClick={() => cancleButton()} >취소</Button>
							<Button onClick={() => submitButton()}>저장</Button>	
						</div>
					</div>}
				</div>
    )
};

export default ResumeSkill;