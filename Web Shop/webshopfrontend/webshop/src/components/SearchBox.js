import React, { useState } from "react";
import { useDispatch } from "react-redux";
export const SearchBox = () => {
  const dispatch = useDispatch();
  const [searchItem, setSearchItem] = useState("");
  const handleFieldChange = (e) => {
    setSearchItem(e.target.value);
  };
  const searchKeySubmit = (e) => {
    dispatch({ type: "setSearchKey", searchKey: searchItem });
  };
  return (
    <div>
      <input
        type="text"
        placeholder="Search Items"
        name="search"
        value={searchItem}
        onChange={handleFieldChange}
      />
      <button id="searchButton" onClick={searchKeySubmit}>Search Item</button>
    </div>
  );
};
