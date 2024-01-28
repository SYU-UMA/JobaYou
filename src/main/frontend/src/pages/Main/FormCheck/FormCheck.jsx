import React from 'react';

import { Input } from 'reactstrap';
import StepDot from '../../../components/StepDot/StepDot';

import styles from './FormCheck.module.scss';

const FormCheck = ({ form }) => {
	return (
		<div className={styles.FormCheckContainer}>
			<h4>💿 데이터 확인</h4>
			<div>
				<div className={styles.FormDiv}>
					<div className={styles.Form}>
						<h5>직무</h5>
						<span>{form.job}</span>
					</div>
					<div className={styles.Form}>
						<h5>연차</h5>
						<span>{form.career}</span>
					</div>
					<div className={styles.Form}>
						<h5>난이도</h5>
						<StepDot maxPage={5} currentPage={form.level} size="lg" />
					</div>
					<div className={styles.Form}>
						<h5>자격 요건</h5>
						<Input
							className={styles.InputRequirements}
							type="textarea"
							placeholder="기업의 자격 요건을 입력해주세요."
							value={form.requirements}
						/>
					</div>
				</div>
			</div>
		</div>
	);
};

export default FormCheck;
