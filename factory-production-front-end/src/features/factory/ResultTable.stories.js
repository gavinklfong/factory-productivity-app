import { Box, Grommet } from "grommet";
import { grommet } from "grommet/themes";
import { ResultTable } from "./ResultTable";
import { DAILY_PRODUCTION_DATA } from "./sample-data";

export default {
    title: 'ResultTable',
    component: ResultTable
}

export const Default = () => (
  <Grommet theme={grommet}>
    <Box align="center" pad="large">
      <ResultTable data={DAILY_PRODUCTION_DATA} />
    </Box>
  </Grommet>
);