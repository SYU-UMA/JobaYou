import React from "react";

import {
  Dropdown as ReactstrapDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";

const Dropdown = ({
  className,
  toggleClassName,
  itemClassName,
  selectedItemText,
  selectItemDatas,
  size,
  color,
}) => {
  const [dropdownOpen, setDropdownOpen] = React.useState(false);

  const toggle = () => setDropdownOpen((prevState) => !prevState);

  return (
    <ReactstrapDropdown
      className={className}
      isOpen={dropdownOpen}
      toggle={toggle}
    >
      <DropdownToggle
        className={toggleClassName}
        size={size}
        color={color}
        caret
      >
        <div />
        {selectedItemText}
      </DropdownToggle>
      <DropdownMenu>
        {selectItemDatas.map((selectItemData, i) => (
          <DropdownItem
            className={itemClassName}
            key={i}
            onClick={selectItemData.onClick}
          >
            {selectItemData.title}
          </DropdownItem>
        ))}
      </DropdownMenu>
    </ReactstrapDropdown>
  );
};

export default Dropdown;
