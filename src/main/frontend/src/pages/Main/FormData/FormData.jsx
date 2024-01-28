import React from 'react';

import Dropdown from '../../../components/Dropdown/Dropdown';

import { Input } from 'reactstrap';

import styles from './FormData.module.scss';

const FormData = ({
	form,
	setForm,
	userData,
	jobItems,
	careerItems,
	levelItems,
}) => {
	const formItems = [
		{
			toggleName: '직무',
			selectItemDatas: jobItems,
			selectedItemText: form.job,
		},
		{
			toggleName: '연차',
			selectItemDatas: careerItems,
			selectedItemText: form.career,
		},
		{
			toggleName: '난이도',
			selectItemDatas: levelItems,
			selectedItemText: form.level,
		},
	];

	const [textareaCount, setTextareaCount] = React.useState(0);
	
	const textareaHandler = (e) => {
		setTextareaCount(e.target.value.length);
	}

	return (
		<div className={styles.Container}>
			<h4>💻 면접 데이터를 입력해주세요.</h4>
			<div className={styles.FormDiv}>
				{formItems.map(({ toggleName, selectItemDatas, selectedItemText }) => (
					<div key={toggleName} className={styles.Form}>
						<h5>{toggleName}</h5>
						<Dropdown
							toggleClassName={styles.DropdownToggle}
							itemClassName={styles.DropdownItem}
							selectItemDatas={selectItemDatas}
							selectedItemText={selectedItemText}
							color="outline-dark"
						/>
					</div>
				))}
				<div className={styles.Form}>
					<h5>자격 요건</h5>
					<Input
						className={styles.InputRequirements}
						type="textarea"
						maxLength={400}
						placeholder="기업의 자격 요건을 입력해주세요."
						value={form.requirements}
						onChange={(e) =>
							{
								setForm((prev) => ({ ...prev, requirements: e.target.value }))
								textareaHandler(e);
							}
						}
					/>
					<div>
						<span>{textareaCount}</span>
						<span>/400 자</span>
					</div>
				</div>
			</div>
		</div>
	);
};

export default FormData;
