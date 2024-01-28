import React from 'react';
import cn from 'classnames';

import styles from './StepDot.module.scss';

const StepDot = ({ maxPage, currentPage, size = 'md' }) => (
	<div className={styles.StepDotWrapper}>
		{Array.from({ length: maxPage }).map((_, step) => (
			<div
				key={`stepDot-${step + 1}`}
				className={cn({
					[styles.StepDot]: true,
					[styles.StepDot__Lg]: size === 'lg',
					[styles['StepDot--active']]: currentPage - 1 === step,
				})}
			/>
		))}
	</div>
);

export default StepDot;
